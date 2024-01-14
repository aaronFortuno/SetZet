package net.estemon.studio.setzet.models.enums

enum class ExerciseType {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION
}

val availableExercises: Map<ExerciseType, List<DifficultyLevel>> = mapOf(
    ExerciseType.ADDITION to listOf(
        DifficultyLevel.PRI0,
        DifficultyLevel.PRI1,
        DifficultyLevel.PRI2,
        DifficultyLevel.PRI3,
        DifficultyLevel.PRI4,
        DifficultyLevel.PRI5,
        DifficultyLevel.PRI6,
        DifficultyLevel.SEC0,
        DifficultyLevel.SEC1,
        DifficultyLevel.SEC2,
        DifficultyLevel.SEC3,
        DifficultyLevel.SEC4
    ),

    ExerciseType.SUBTRACTION to listOf(
        DifficultyLevel.PRI0,
        DifficultyLevel.PRI1,
        DifficultyLevel.PRI2,
        DifficultyLevel.PRI3,
        DifficultyLevel.PRI4,
        DifficultyLevel.PRI5,
        DifficultyLevel.PRI6,
        DifficultyLevel.SEC0,
        DifficultyLevel.SEC1,
        DifficultyLevel.SEC2,
        DifficultyLevel.SEC3,
        DifficultyLevel.SEC4
    ),

    ExerciseType.MULTIPLICATION to listOf(
        DifficultyLevel.PRI3,
        DifficultyLevel.PRI4,
        DifficultyLevel.PRI5,
        DifficultyLevel.PRI6,
        DifficultyLevel.SEC0,
        DifficultyLevel.SEC1,
        DifficultyLevel.SEC2,
        DifficultyLevel.SEC3,
        DifficultyLevel.SEC4
    ),

    ExerciseType.DIVISION to listOf(
        DifficultyLevel.PRI4,
        DifficultyLevel.PRI5,
        DifficultyLevel.PRI6,
        DifficultyLevel.SEC0,
        DifficultyLevel.SEC1,
        DifficultyLevel.SEC2,
        DifficultyLevel.SEC3,
        DifficultyLevel.SEC4
    )
)