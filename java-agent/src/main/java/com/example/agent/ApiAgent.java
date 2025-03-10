package com.example.agent;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
/**
 * @author lxx
 * @create 2025-03-10-16:55
 */
public class ApiAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent loaded at startup!");
        getApiInfo(inst, "startup");
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent loaded dynamically!");
        getApiInfo(inst, "runtime");
    }

    /**
     * 获取所有包的 API 信息
     *
     * @param inst      Instrumentation 对象
     * @param loadMode  加载模式（startup 或 runtime）
     */
    private static void getApiInfo(Instrumentation inst, String loadMode) {
        List<ApiInfo> apiList = new ArrayList<>();
        for (Class<?> clazz : inst.getAllLoadedClasses()) {
            // 获取类所在的包名
            String packageName = clazz.getPackage() != null ? clazz.getPackage().getName() : "";
            // 记录所有类的方法信息
            for (Method method : clazz.getDeclaredMethods()) {
                ApiInfo apiInfo = new ApiInfo(
                        clazz.getName(),
                        method.getName(),
                        method.getReturnType().getName(),
                        getParameterTypes(method.getParameterTypes())
                );
                apiList.add(apiInfo);
            }
        }
        // 根据加载模式保存 API 信息
        saveToJson(apiList, loadMode);
    }

    private static String[] getParameterTypes(Class<?>[] parameterTypes) {
        String[] types = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            types[i] = parameterTypes[i].getName();
        }
        return types;
    }

    /**
     * 将 API 信息保存为 JSON 文件
     *
     * @param apiList   API 信息列表
     * @param loadMode  加载模式（startup 或 runtime）
     */
    private static void saveToJson(List<ApiInfo> apiList, String loadMode) {
        Gson gson = new Gson();
        String json = gson.toJson(apiList);
        String fileName = "api_info_" + loadMode + ".json";
        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            writer.write(json);
            System.out.println("API info saved to " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

