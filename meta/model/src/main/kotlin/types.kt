package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*
import kotlinx.serialization.Serializable
import java.io.Serializable as JvmSerializable

// Inner Any can be String and Boolean for __viewAlias and Boolean for others
typealias InkrementalQuirks = MutableMap<String, Map<String, Any?>>
typealias InkrementalTransformers = Map<String, Map<String, List<DslTransformer>>>

enum class InkrementalType { SDK, LIBRARY }
enum class InkrementalPlatform { ANDROID, IOS }

@Serializable
sealed class DslTransformer : JvmSerializable {
    /**
     * @return True if you want this transformer to stop execution of regular dsl generator
     */
    open fun handleTransformersForDsl(builder: FunSpec.Builder, attrModel: AttrModel, attr: MemberName): Boolean {
        return false
    }
    /**
     * @return True if you want this transformer to stop execution of regular dsl generator
     */
    open fun handleTransformersForAttrSetter(builder: CodeBlock.Builder, owner: ViewModel, v: String, setterName: String, argAsParam: String): Boolean {
        return false
    }

    @Serializable
    object FloatPixelToDipSizeTransformer : DslTransformer() {

        override fun handleTransformersForDsl(builder: FunSpec.Builder, attrModel: AttrModel, attr: MemberName): Boolean {
            if (attrModel.type.argType.toString() == Float::class.qualifiedName){
                builder.addParameter("arg", ClassName.bestGuess("dev.inkremental.dsl.android.Dip"))
                builder.returns(UNIT)
                builder.addCode(CodeBlock.of("return %M(%S, arg.value)", attr, attrModel.name))
                return true
            }
            return false
        }

        override fun handleTransformersForAttrSetter(builder: CodeBlock.Builder, owner: ViewModel, v: String, setterName: String, argAsParam: String): Boolean {
            builder.beginControlFlow(
                    "v is %T && arg is Int ->",
                    owner.starProjectedType
            )
            builder.addStatement("$v.$setterName(%M($argAsParam).toFloat())", MemberName(PACKAGE, "dip"))
            return true
        }

    }
    @Serializable
    data class RequiresApiTransformer(val api : Int) : DslTransformer() {
        override fun handleTransformersForDsl(builder: FunSpec.Builder, attrModel: AttrModel, attr: MemberName): Boolean {
            builder.addAnnotation(AnnotationSpec.builder(androidx.annotation.RequiresApi::class)
                    .addMember("api = $api")
                    .build())
            return false
        }
    }
}