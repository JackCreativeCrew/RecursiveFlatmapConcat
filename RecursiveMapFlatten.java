package au.com.cc.resconfig.util;

import java.util.HashMap;
import java.util.Map;

public class RecursiveMapFlatten {
    public static Map<String, String> convertNestedToNonNestedMap(Map<String, Object> nestedMap) {
        Map<String, String> result = new HashMap<>();

        convertNestedToNonNestedMap(nestedMap, result, "");

        return result;
    }

    private static void convertNestedToNonNestedMap(Map<String, Object> nestedMap,
                                                    Map<String, String> result,
                                                    String parentNames) {
        for (Map.Entry<String, Object> entry : nestedMap.entrySet()) {
            if (entry.getValue() instanceof Map<?, ?>) {
                parentNames += parentNames.length() > 0 ? "." + entry.getKey() : entry.getKey();
                convertNestedToNonNestedMap((Map<String, Object>) entry.getValue(),
                                            result,
                                            parentNames);
            }
            else {
                if (parentNames.length() > 0)
                    result.put(parentNames + "." + entry.getKey(), entry.getValue().toString());
                else
                    result.put(entry.getKey(), entry.getValue().toString());
            }
        }
    }
}
