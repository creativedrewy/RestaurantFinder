package com.creativedrewy.restaurantfinder

import android.content.res.Resources
import com.creativedrewy.restaurantfinder.api.RestaurantDto
import com.creativedrewy.restaurantfinder.api.RestaurantStatusDto
import com.creativedrewy.restaurantfinder.viewmodel.ListItemViewStateMapping
import com.creativedrewy.restaurantfinder.viewmodel.RestaurantDetails
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertTrue

class ListItemViewStateMappingTest: Spek({

    Feature("Mapping restaurant list DTOs to view state list items") {

        val sampleString by memoized { "sample" }
        val testId by memoized { 10 }
        val testName by memoized { "Test Name" }
        val testDesc by memoized { "description" }
        val testUrl by memoized { "www.doordash.com" }

        val baseDto by memoized {
            RestaurantDto(
                id = testId,
                name = testName,
                description = testDesc,
                coverImgUrl = testUrl,
                status = RestaurantStatusDto(null, listOf())
            )
        }

        val mockResources by memoized {
            mock<Resources>() {
                on { getString(any()) } doReturn sampleString
            }
        }

        val stateMapping by memoized { ListItemViewStateMapping(mockResources) }

        Scenario("Validating simple view state props") {
            lateinit var dto: RestaurantDto
            lateinit var result: RestaurantDetails

            Given("Simple dto properties") {
                dto = baseDto
            }

            When("Mapping the view state") {
                result = stateMapping.mapDtoToViewState(dto)
            }

            Then("The non-status props are set correctly") {
                assertTrue { result.displayName == testName }
                assertTrue { result.desc == testDesc }
                assertTrue { result.imageUrl == testUrl }
            }
        }

        Scenario("The API returns a restaurant is closed") {
            lateinit var dto: RestaurantDto
            lateinit var result: RestaurantDetails

            Given("A closed restaurant") {
                dto = baseDto.copy(
                    status = RestaurantStatusDto("something", listOf())
                )
            }

            When("Mapping the view state") {
                result = stateMapping.mapDtoToViewState(dto)
            }

            Then("The status is closed") {
                assertTrue { result.status == sampleString }
            }
        }

        Scenario("The API returns only one asap range value") {
            lateinit var dto: RestaurantDto
            lateinit var result: RestaurantDetails

            Given("Just one asap range value") {
                dto = baseDto.copy(
                    status = RestaurantStatusDto(null, listOf(5))
                )
            }

            When("Mapping the view state") {
                result = stateMapping.mapDtoToViewState(dto)
            }

            Then("The single asap value is returned in the display string") {
                assertTrue { result.status == "5 sample" }
            }
        }

        Scenario("The API returns two asap range values") {
            lateinit var dto: RestaurantDto
            lateinit var result: RestaurantDetails

            Given("Two asap range values") {
                dto = baseDto.copy(
                    status = RestaurantStatusDto(null, listOf(10, 20))
                )
            }

            When("Mapping the view state") {
                result = stateMapping.mapDtoToViewState(dto)
            }

            Then("The average of the range values is returned in the display string") {
                assertTrue { result.status == "15 sample" }
            }
        }

        Scenario("The API returns an invalid restaurant status") {
            lateinit var dto: RestaurantDto
            lateinit var result: RestaurantDetails

            Given("An invalid restaurant status") {
                dto = baseDto.copy(
                    status = RestaurantStatusDto(null, listOf())
                )
            }

            When("Mapping the view state") {
                result = stateMapping.mapDtoToViewState(dto)
            }

            Then("The status is empty") {
                assertTrue { result.status == "" }
            }
        }
    }
})