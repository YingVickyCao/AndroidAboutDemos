#include <jni.h>
#include <string>

// MainActivity
extern "C" JNIEXPORT jstring JNICALL
    Java_com_example_hades_generateso_MainActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_example_hades_generateso_MainActivity_sum(JNIEnv *env, jobject /* this */, jint a, jint b) {
    return a + b;
}

// NativeLibUtil.java
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_hades_generateso_NativeLibUtil_stringFromJNI(JNIEnv *env, jclass type) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_hades_generateso_NativeLibUtil_sum(JNIEnv *env, jclass type, jint a, jint b) {
    return a + b;
}