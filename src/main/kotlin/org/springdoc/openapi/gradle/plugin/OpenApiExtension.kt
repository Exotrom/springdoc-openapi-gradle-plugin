package org.springdoc.openapi.gradle.plugin

import org.gradle.api.Action
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import javax.inject.Inject

open class OpenApiExtension @Inject constructor(
	objects: ObjectFactory,
) {
	val apiDocsUrl: Property<String> = objects.property(String::class.java)
	val outputFileName: Property<String> = objects.property(String::class.java)
	val outputDir: DirectoryProperty = objects.directoryProperty()
	val waitTimeInSeconds: Property<Int> = objects.property(Int::class.java)
	val groupedApiMappings: MapProperty<String, String> =
		objects.mapProperty(String::class.java, String::class.java)
	val customBootRun: CustomBootRunAction =
		objects.newInstance(CustomBootRunAction::class.java)

	fun customBootRun(action: Action<CustomBootRunAction>) {
		action.execute(customBootRun)
	}
}

open class CustomBootRunAction @Inject constructor(
	objects: ObjectFactory,
) {
	val systemProperties: MapProperty<String, Any> =
		objects.mapProperty(String::class.java, Any::class.java)
	val workingDir: DirectoryProperty = objects.directoryProperty()
	val mainClass: Property<String> = objects.property(String::class.java)
	val args: ListProperty<String> = objects.listProperty(String::class.java)
	val classpath: ConfigurableFileCollection = objects.fileCollection()
	val jvmArgs: ListProperty<String> = objects.listProperty(String::class.java)
	val environment: MapProperty<String, Any> =
		objects.mapProperty(String::class.java, Any::class.java)
}
