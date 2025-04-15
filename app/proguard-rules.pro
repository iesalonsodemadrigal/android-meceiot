-keepattributes *Annotation*
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

### ROOM
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

## Models API/Firebase
-keep class edu.iesam.meceiot.features.alerts.data.remote.PanelApiModel { *; }
-keep class edu.iesam.meceiot.features.alerts.data.remote.SensorApiModel { *; }
-keep class edu.iesam.meceiot.features.alerts.data.remote.TypeSensorApiModel { *; }
-keep class edu.iesam.meceiot.features.alerts.data.local.db.AlertEntity { *; }

-keep class edu.iesam.meceiot.features.developer.data.remote.api.DeveloperApiModel { *; }
-keep class edu.iesam.meceiot.features.developer.data.local.db.DeveloperEntity { *; }

-keep class edu.iesam.meceiot.features.externalresources.data.remote.api.ExternalResourcesApiModel { *; }
-keep class edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesEntity { *; }

-keep class edu.iesam.meceiot.features.pantallasensor.data.remote.SensorGraphApiModel { *; }
-keep class edu.iesam.meceiot.features.sensorpanels.data.remote.PanelApiModel { *; }
-keep class edu.iesam.meceiot.features.sensorpanels.data.remote.SensorApiModel { *; }
-keep class edu.iesam.meceiot.features.sensorpanels.data.local.db.PanelEntity { *; }
-keep class edu.iesam.meceiot.features.sensorpanels.data.local.db.SensorEntity { *; }

-keep class edu.iesam.meceiot.features.grafana.data.models.dashboard.TargetDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.dashboard.PanelDetailDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.dashboard.MetaDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.dashboard.DashboardDetailResponseDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.dashboard.DashboardDetailDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.search.DashboardSummary { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.search.DashboardResponseDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxFieldDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxFrameDataDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxFrameDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxFrameSchemaDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryRequestDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryResponseDto { *; }
-keep class edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryResultDto { *; }
-keep class edu.iesam.meceiot.features.login.domain.LoginCredentials { *; }

### Firebase Auth
-keep class com.google.android.gms.internal.** { *; }

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*


##---------------Begin: proguard configuration for Gson  ----------
# Gson specific classes
-dontwarn sun.misc.**

#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
# -keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

##---------------End: proguard configuration for Gson  ----------