package presentation.mealManagement

import presentation.mealManagement.model.IMealCreationScreenModel
import presentation.mealManagement.model.IMealEditorScreenModel


class MealScreenModelFactory {
    fun create(screenMode: ScreenMode, mealId: String): IMealBehavior {
        return when (screenMode) {
            ScreenMode.CREATION -> IMealCreationScreenModel()
            ScreenMode.EDIT -> IMealEditorScreenModel(mealId)
        }
    }
}
