package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

fun ParameterModel.toParameterSpec(): ParameterSpec = ParameterSpec.builder(name, type).build()

fun FunctionModel.parameterSpecs() = this.parameters.map(ParameterModel::toParameterSpec)

fun FunctionModel.asLambdaTypeName(): LambdaTypeName =
    LambdaTypeName.get(
        null,
        parameters.map(ParameterModel::toParameterSpec),
        returnType
    )

const val PACKAGE = "dev.inkremental"
const val ROOT_VIEW_SCOPE = "RootViewScope"

val ANY_N: ClassName = ANY.copy(nullable = true)
val FUNCTION_STAR: TypeName = ClassName("kotlin", "Function").parameterizedBy(STAR)
val ANVIL: ClassName = ClassName(PACKAGE, "Anvil")
val VIEW: ClassName = ClassName("android.view", "View")
