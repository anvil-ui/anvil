package dev.inkremental.meta.introspect.ios

import org.jetbrains.kotlin.config.*
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies
import org.jetbrains.kotlin.konan.library.KonanFactories.DefaultDeserializedDescriptorFactory
import org.jetbrains.kotlin.konan.library.konanPlatformLibraryPath
import org.jetbrains.kotlin.konan.library.lite.LiteKonanDistributionProvider
import org.jetbrains.kotlin.konan.library.lite.LiteKonanLibraryFacade
import org.jetbrains.kotlin.konan.target.buildDistribution
import org.jetbrains.kotlin.library.impl.createKotlinLibrary
import org.jetbrains.kotlin.library.resolver.impl.libraryResolver
import org.jetbrains.kotlin.library.resolverByName
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.descriptorUtil.*
import org.jetbrains.kotlin.storage.LockBasedStorageManager
import org.jetbrains.kotlin.util.Logger
import java.io.File
import org.jetbrains.kotlin.konan.file.File as KFile

fun main() {
    // org.jetbrais.kotlin.gradle.utils.NativeCompilerDownloader
    val distRoot = File(System.getenv("HOME")!!)
        .resolve(".konan/kotlin-native-macos-1.3.60")

    val distribution = buildDistribution(distRoot.absolutePath)
    println("Home: ${distribution.konanHome}")
    println("klib: ${distribution.klib}")

    val logger = SimpleLogger()
    val resolver = resolverByName(
        emptyList(),
        distributionKlib = distribution.klib,
        skipCurrentDir = true,
        logger = logger)

    val libResolver = resolver.libraryResolver()
    println("Repo roots:")
    libResolver.searchPathResolver.searchRoots.forEach {
        println("\t${it.absolutePath}")
    }

    val liteDistribution = LiteKonanDistributionProvider.getDistribution(distRoot)!!
    println("Lite ver: ${liteDistribution.konanVersionString}")
    val libProvider = LiteKonanLibraryFacade.getDistributionLibraryProvider(distRoot)
    val konanLibrary = libProvider.getLibrary(distRoot.resolve(konanPlatformLibraryPath("UIKit", "ios_arm64")))!!
    println("Konan lib name: ${konanLibrary.name}, platform: ${konanLibrary.platform}")
    println("Konan lib paths: ${konanLibrary.sourcePaths}")

    val library = createKotlinLibrary(KFile(distRoot.resolve(konanPlatformLibraryPath("UIKit", "ios_arm64")).absolutePath))

    val storageManager = LockBasedStorageManager("InkrementalStorage")
    val versionSpec = LanguageVersionSettingsImpl(LanguageVersion.KOTLIN_1_3, ApiVersion.KOTLIN_1_3)

    val moduleDescriptor = DefaultDeserializedDescriptorFactory.createDescriptorAndNewBuiltIns(
        library, versionSpec, storageManager, null)

    val defaultModules = resolver.defaultLinks(false, true, true)
        .map {
            println("Default module: ${it.libraryName}")
            DefaultDeserializedDescriptorFactory.createDescriptor(
                it, versionSpec, storageManager, moduleDescriptor.builtIns, null)
        }

    (defaultModules + moduleDescriptor).let { allModules ->
        allModules.forEach { it.setDependencies(allModules) }
    }

    println("Library name: ${library.libraryName}, files: ${library.fileCount()}")

    moduleDescriptor.accept(PrintVisitor(), Unit)
}

class PrintVisitor : DeclarationDescriptorVisitorEmptyBodies<Unit, Unit>() {
    override fun visitModuleDeclaration(descriptor: ModuleDescriptor, data: Unit) {
        val fragments = descriptor.getPackageFragments()
        println("Module: ${descriptor.name}, fragments: ${fragments.size}")
        fragments.forEach { it.accept(this, data) }
    }

    override fun visitPackageFragmentDescriptor(descriptor: PackageFragmentDescriptor, data: Unit) {
        val children = descriptor.getMemberScope().getContributedDescriptors()//.filter { it.shouldBePrinted }
        if (children.isNotEmpty()) {
            //val packageName = descriptor.fqName.let { if (it.isRoot) "<root>" else it.asString() }
            //println("Package $packageName")
            children.forEach { it.accept(this, data) }
        }
    }

    override fun visitClassDescriptor(descriptor: ClassDescriptor, data: Unit) {
        val isViewClass = descriptor.getAllSuperclassesWithoutAny().firstOrNull { it.fqNameSafe == FqName("platform.UIKit.UIView") } != null
        if(isViewClass) {
            println("Class: ${descriptor.name.asString()} | ${descriptor.kind.name}")
            val children = descriptor.unsubstitutedMemberScope.getContributedDescriptors()
            val constructors = descriptor.constructors
            if (children.isNotEmpty() || constructors.isNotEmpty()) {
                println("\tctors:")
                constructors.forEach { it.accept(this, data) }
                println("\tchildren:")
                children.forEach { it.accept(this, data) }
            }
        }
    }

    override fun visitFunctionDescriptor(descriptor: FunctionDescriptor, data: Unit) {
        if(descriptor.name.asString().startsWith("set") && descriptor.kind != CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            println("\t\tfun: ${descriptor.name} | ${descriptor.kind}")
        }
    }
}

private fun getPackagesFqNames(module: ModuleDescriptor): Set<FqName> {
    val result = mutableSetOf<FqName>()

    fun getSubPackages(fqName: FqName) {
        result.add(fqName)
        module.getSubPackagesOf(fqName) { true }.forEach { getSubPackages(it) }
    }

    getSubPackages(FqName.ROOT)
    return result
}

fun ModuleDescriptor.getPackageFragments(): List<PackageFragmentDescriptor> =
    getPackagesFqNames(this).flatMap {
        getPackage(it).fragments.filter { it.module == this }
    }

class SimpleLogger(
    private val tag: String = "deserializer"
) : Logger {
    private fun log(level: String, message: String) = println("$level: [$tag] $message")
    override fun log(message: String) = log("v", message)
    override fun warning(message: String) = log("w", message)
    override fun error(message: String) = log("e", message)
    override fun fatal(message: String): Nothing {
        log("v", message)
        kotlin.error(message)
    }
}
