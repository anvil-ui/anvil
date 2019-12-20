package dev.inkremental.meta.gradle

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import dev.inkremental.meta.model.FunctionModel
import dev.inkremental.meta.model.ParameterModel
import java.lang.reflect.TypeVariable
import kotlin.reflect.KFunction
import kotlin.reflect.KTypeParameter
import kotlin.reflect.full.valueParameters

internal fun KFunction<*>.toFunctionModel(): FunctionModel = FunctionModel(
    name = name,
    parameters = valueParameters.mapIndexed { index, parameter ->
        ParameterModel(parameter.name ?: "a$index", parameter.type.asTypeName())
    },
    returnType = returnType.asTypeName()
)

internal fun ParameterModel.toParameterSpec(): ParameterSpec = ParameterSpec.builder(name, type).build()

internal fun FunctionModel.asLambdaTypeName(): LambdaTypeName =
    LambdaTypeName.get(
        null,
        parameters.map(ParameterModel::toParameterSpec),
        returnType
    )

internal fun Class<*>.asParameterizedType(): ParameterizedTypeName? = when {
    isArray && !componentType.isPrimitive -> kotlin.parameterizedBy(componentType.kotlin)
    typeParameters.isEmpty() -> null
    else -> kotlin.asClassName().parameterizedBy(kotlin.typeParameters)
}

internal fun ClassName.parameterizedBy(typeParameters: List<KTypeParameter>): ParameterizedTypeName =
    parameterizedBy(*typeParameters.map { it.upperBounds[0].asTypeName() }.toTypedArray())

internal fun ClassName.parameterizedBy(typeParameters: Array<out TypeVariable<out Class<*>>>): ParameterizedTypeName =
    parameterizedBy(*typeParameters.map { it.bounds[0].asTypeName() }.toTypedArray())

internal val ANY_N: ClassName = ANY.copy(nullable = true)
internal val FUNCTION_STAR: TypeName = ClassName("kotlin", "Function").parameterizedBy(STAR)
internal val ANVIL: ClassName = ClassName(PACKAGE, "Anvil")
internal val VIEW: ClassName = ClassName("android.view", "View")
