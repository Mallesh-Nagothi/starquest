#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "SaltGenerator.h"

JNIEXPORT jint JNICALL Java_com_starquest_usermanagement_SaltGenerator_getSalt(JNIEnv *env, jobject obj) {
  srand(time(0));
  return rand();
}
