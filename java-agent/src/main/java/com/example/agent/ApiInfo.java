package com.example.agent;

/**
 * @author lxx
 * @create 2025-03-10-16:54
 */
public class ApiInfo {
        private String className;
        private String methodName;
        private String returnType;
        private String[] parameterTypes;

        public ApiInfo(String className, String methodName, String returnType, String[] parameterTypes) {
            this.className = className;
            this.methodName = methodName;
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
        }

    // Getters
    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    // Setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
    public String toString() {
        return "ApiInfo{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + java.util.Arrays.toString(parameterTypes) +
                '}';
    }
}



