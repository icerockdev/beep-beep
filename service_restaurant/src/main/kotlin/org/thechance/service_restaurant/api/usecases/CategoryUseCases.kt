package org.thechance.service_restaurant.api.usecases

import org.koin.core.annotation.Single
import org.thechance.service_restaurant.entity.Category


@Single
data class CategoryUseCasesContainer(
    val getCategories: GetCategoriesUseCases,
    val getCategoryDetails: GetCategoryDetailsUseCase,
    val addCategory: CreateCategoryUseCases,
    val updateCategory: UpdateCategoryUseCases,
    val deleteCategory: DeleteCategoryUseCases
)

interface GetCategoriesUseCases {
    suspend operator fun invoke(): List<Category>
}

interface GetCategoryDetailsUseCase {
    suspend operator fun invoke(categoryId: String): Category
}

interface CreateCategoryUseCases {
    suspend operator fun invoke(category: Category): Boolean
}

interface UpdateCategoryUseCases {
    suspend operator fun invoke(category: Category): Boolean
}

interface DeleteCategoryUseCases {
    suspend operator fun invoke(categoryId: String): Boolean
}
