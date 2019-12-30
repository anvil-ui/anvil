package dev.inkremental.meta.model

import com.squareup.kotlinpoet.*

inline fun FunSpec.Builder.addCode(builderAction: CodeBlock.Builder.() -> Unit) =
    addCode(buildCodeBlock(builderAction))

inline fun buildFunSpec(name: String, builderAction: FunSpec.Builder.() -> Unit): FunSpec =
    FunSpec.builder(name).apply(builderAction).build()

inline fun FileSpec.Builder.addFunction(name: String, builderAction: FunSpec.Builder.() -> Unit) =
    addFunction(buildFunSpec(name, builderAction))

inline fun TypeSpec.Builder.addFunction(name: String, builderAction: FunSpec.Builder.() -> Unit) =
    addFunction(buildFunSpec(name, builderAction))

inline fun buildParameterSpec(
    name: String,
    type: TypeName,
    vararg modifiers: KModifier,
    builderAction: ParameterSpec.Builder.() -> Unit
): ParameterSpec =
    ParameterSpec.builder(name, type, *modifiers).apply(builderAction).build()

inline fun FunSpec.Builder.addParameter(
    name: String,
    type: TypeName,
    vararg modifiers: KModifier,
    builderAction: ParameterSpec.Builder.() -> Unit
) =
    addParameter(buildParameterSpec(name, type, *modifiers, builderAction = builderAction))

inline fun buildClass(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.classBuilder(name).apply(builderAction).build()

inline fun buildClass(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.classBuilder(className).apply(builderAction).build()

inline fun buildObject(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.objectBuilder(name).apply(builderAction).build()

inline fun buildObject(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.objectBuilder(className).apply(builderAction).build()

inline fun buildCompanionObject(name: String? = null, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.companionObjectBuilder(name).apply(builderAction).build()

inline fun buildInterface(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.interfaceBuilder(name).apply(builderAction).build()

inline fun buildInterface(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.interfaceBuilder(className).apply(builderAction).build()

inline fun buildEnum(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.enumBuilder(name).apply(builderAction).build()

inline fun buildEnum(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.enumBuilder(className).apply(builderAction).build()

inline fun buildAnonymousClass(builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.anonymousClassBuilder().apply(builderAction).build()

inline fun buildAnnotationType(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.annotationBuilder(name).apply(builderAction).build()

inline fun buildAnnotationType(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec =
    TypeSpec.annotationBuilder(className).apply(builderAction).build()

inline fun buildAnnotation(type: ClassName, builderAction: AnnotationSpec.Builder.() -> Unit): AnnotationSpec =
    AnnotationSpec.builder(type).apply(builderAction).build()

inline fun FileSpec.Builder.addClass(name: String, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildClass(name, builderAction))

inline fun FileSpec.Builder.addClass(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildClass(className, builderAction))

inline fun FileSpec.Builder.addObject(name: String, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildObject(name, builderAction))

inline fun FileSpec.Builder.addObject(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildObject(className, builderAction))

inline fun FileSpec.Builder.addInterface(name: String, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildInterface(name, builderAction))

inline fun FileSpec.Builder.addInterface(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildInterface(className, builderAction))

inline fun FileSpec.Builder.addEnum(name: String, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildEnum(name, builderAction))

inline fun FileSpec.Builder.addEnum(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildEnum(className, builderAction))

inline fun FileSpec.Builder.addAnonymousClass(builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildAnonymousClass(builderAction))

inline fun FileSpec.Builder.addAnnotationType(name: String, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildAnnotationType(name, builderAction))

inline fun FileSpec.Builder.addAnnotationType(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): FileSpec.Builder =
    addType(buildAnnotationType(className, builderAction))

inline fun FileSpec.Builder.addAnnotation(type: ClassName, builderAction: AnnotationSpec.Builder.() -> Unit): FileSpec.Builder =
    addAnnotation(buildAnnotation(type, builderAction))

inline fun TypeSpec.Builder.addClass(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildClass(name, builderAction))

inline fun TypeSpec.Builder.addClass(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildClass(className, builderAction))

inline fun TypeSpec.Builder.addObject(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildObject(name, builderAction))

inline fun TypeSpec.Builder.addObject(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildObject(className, builderAction))

inline fun TypeSpec.Builder.addCompanionObject(name: String? = null, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildCompanionObject(name, builderAction))

inline fun TypeSpec.Builder.addInterface(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildInterface(name, builderAction))

inline fun TypeSpec.Builder.addInterface(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildInterface(className, builderAction))

inline fun TypeSpec.Builder.addEnum(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildEnum(name, builderAction))

inline fun TypeSpec.Builder.addEnum(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildEnum(className, builderAction))

inline fun TypeSpec.Builder.addAnonymousClass(builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildAnonymousClass(builderAction))

inline fun TypeSpec.Builder.addAnnotationType(name: String, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildAnnotationType(name, builderAction))

inline fun TypeSpec.Builder.addAnnotationType(className: ClassName, builderAction: TypeSpec.Builder.() -> Unit): TypeSpec.Builder =
    addType(buildAnnotationType(className, builderAction))

inline fun TypeSpec.Builder.addAnnotation(type: ClassName, builderAction: AnnotationSpec.Builder.() -> Unit): TypeSpec.Builder =
    addAnnotation(buildAnnotation(type, builderAction))

inline fun TypeSpec.Builder.addInitializerBlock(builderAction: CodeBlock.Builder.() -> Unit): TypeSpec.Builder =
    addInitializerBlock(buildCodeBlock(builderAction))
