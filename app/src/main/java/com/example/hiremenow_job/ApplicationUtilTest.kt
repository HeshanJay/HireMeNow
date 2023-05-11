package com.example.hiremenow_job

import org.junit.Test

class ApplicationUtilTest {

    // Nested class for testing the FormUtil functions
    class FormUtilTest {

        @Test
        fun `empty cname returns false`() {
            // Test case: Empty cname, other fields filled
            // Validate the form input using ApplicationUtil.validateFormInput
            val result = ApplicationUtil.validateFormInput(
                "",
                "Heshan Jayasundara",
                "hesh@gmail.com",
                "0768565432",
            )
            assertThat(result).isFalse()
            // Assert that the result is false, indicating invalid form input
        }

        @Test
        fun `valid cname, fname, email, phone returns true`() {
            // Test case: All fields filled with valid values
            // Validate the form input using ApplicationUtil.validateFormInput
            val result = ApplicationUtil.validateFormInput(
                "Praneeth",
                "Heshani Jayasundara",
                "hesh2@gmail.com",
                "0768569432",
            )
            assertThat(result).isTrue()
            // Assert that the result is true, indicating valid form input
        }

        @Test
        fun `email already exists returns false`() {
            // Test case: Email already exists in the system
            // Validate the form input using ApplicationUtil.validateFormInput
            val result = ApplicationUtil.validateFormInput(
                "Sasrika",
                "Jayasundara",
                "heshan@gmail.com",
                "0768535432",
            )
            assertThat(result).isFalse()
            // Assert that the result is false, indicating invalid form input
        }

        @Test
        fun `phone already exists returns false`() {
            // Test case: Phone number already exists in the system
            // Validate the form input using ApplicationUtil.validateFormInput
            val result = ApplicationUtil.validateFormInput(
                "Hansaka",
                "Hansika",
                "nimsara@gmail.com",
                "0764657843",
            )
            assertThat(result).isFalse()
            // Assert that the result is false, indicating invalid form input
        }

        @Test
        fun `phone less than 10 digits returns false`() {
            // Test case: Phone number with less than 10 digits
            // Validate the form input using ApplicationUtil.validateFormInput
            val result = ApplicationUtil.validateFormInput(
                "Jayanidu",
                "Dias",
                "dias@gmail.com",
                "076856543",
            )
            assertThat(result).isFalse()
            // Assert that the result is false, indicating invalid form input
        }
    }
}
