package com.market.bot.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.market.bot.dto.SysPermission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TreeUtil {

    /**
     * 菜单树
     * @param parentId
     * @param permissionsAll
     * @param array
     */
    public static void setPermissionsTree(Integer parentId, List<SysPermission> permissionsAll, JSONArray array){
        for (SysPermission per : permissionsAll) {
            if (per.getParentId().equals(parentId)) {
                String jsonString = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(jsonString);
                array.add(parent);
                if(permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny().isPresent()){
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(parentId, permissionsAll, child);
                }
            }
        }
    }
}
