package com.contactabilidad.push;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

public class UserMapper {
    public Map<String, AttributeValue> toMap(User user){
        Map<String, AttributeValue> map = new HashMap<>();
        map.put("uuid",AttributeValue.builder().s(user.getUuid()).build());
        map.put("name",AttributeValue.builder().s(user.getName()).build());
        map.put("device",AttributeValue.builder().s(user.getDevice()).build());
        return map;
    }

    public User fromMap( Map<String, AttributeValue>  map){
        User user = new User();
        user.setUuid(map.get("uuid").s());
        user.setDevice(map.get("device").s());
        user.setName(map.get("name").s());
        return user;
    }
}
