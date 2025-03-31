package com.finclusion.userservice.repository;

import com.finclusion.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final DynamoDbClient dynamoDbClient;
    public DynamoDbTable<User> getUserTable() {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        return enhancedClient.table("user-table", TableSchema.fromBean(User.class));
    }

    public void saveUser(User user) {
        getUserTable().putItem(user);
    }

    public User getUserById(String userId) {
        return getUserTable().getItem(r -> r.key(k -> k.partitionValue(userId)));
    }

    public User getUserByEmail(String email) {
        // Scan entire table (not efficient in production → we will optimize later)
        return getUserTable().scan().items()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

}
