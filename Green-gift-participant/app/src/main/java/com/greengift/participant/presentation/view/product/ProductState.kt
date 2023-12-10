package com.greengift.participant.presentation.view.product

import com.greengift.participant.data.dto.ProductAllDTO

data class ProductState (
    val isLoading: Boolean = false,
    val productList: List<ProductAllDTO> = emptyList(),
    val error: String = ""
)