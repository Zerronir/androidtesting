package com.example.testapp.validator

import com.example.testapp.R
import com.example.testapp.validator.base.BaseValidator
import com.example.testapp.validator.base.ValidateResult

class EmptyValidator(val input: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid = input.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_empty_field
        )
    }
}