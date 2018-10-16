//
// Created by hades on 17/10/2018.
//

#include <jni.h>
#include <string>

//com.example.hades.testndk

extern "C"
jstring
Java_com_example_hades_testndk_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
