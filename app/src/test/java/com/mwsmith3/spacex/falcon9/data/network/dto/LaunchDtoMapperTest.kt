package com.mwsmith3.spacex.falcon9.data.network.dto

import com.mwsmith3.spacex.falcon9.domain.model.Falcon9Launch
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Test

class LaunchDtoMapperTest {

    private val mapper = LaunchDtoMapper()

    @Test
    fun `when map dto then expected Falcon9Launch returned`() {
        val dto = listOf(
            LaunchDto(
                name = "name 1",
                success = true,
                links = LaunchDto.LinksDto(
                    patch = LaunchDto.LinksDto.PatchDto(
                        small = "some-url"
                    )
                ),
                launchDate = "2010-12-01"
            )
        )
        val expected = listOf(
            Falcon9Launch(
                name = "name 1",
                launchDate = DateTime.parse("2010-12-01"),
                success = true,
                imageUrl = "some-url"
            )
        )
        val actual = mapper.map(dto)
        assertThat(actual).isEqualTo(expected)
    }
}
