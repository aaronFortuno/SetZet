package net.estemon.studio.setzet.models

import net.estemon.studio.setzet.models.addition.*
import net.estemon.studio.setzet.models.enums.DifficultyLevel
import net.estemon.studio.setzet.models.enums.ExerciseType

data class StdExerciseKey(
    val type: ExerciseType,
    val difficultyLevel: DifficultyLevel
)

val standardExercises: Map<StdExerciseKey, List<Pair<Int, Int>>> = mapOf(
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.PRI0) to additionPri0,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.PRI1) to additionPri1,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.PRI2) to additionPri2,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.PRI3) to additionPri3,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.PRI4) to additionPri4,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.PRI5) to additionPri5,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.PRI6) to additionPri6,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.SEC0) to additionSec0,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.SEC1) to additionSec1,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.SEC2) to additionSec2,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.SEC3) to additionSec3,
    StdExerciseKey(ExerciseType.ADDITION, DifficultyLevel.SEC4) to additionSec4
    )
