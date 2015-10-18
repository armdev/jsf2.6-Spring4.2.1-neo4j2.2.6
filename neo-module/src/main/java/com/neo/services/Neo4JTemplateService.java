package com.neo.services;

import com.neo.domain.User;
import com.neo.util.SocialNetworkUniverse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
//import org.springframework.data.neo4j.support.node.;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class Neo4JTemplateService {

    @Autowired
    private Neo4jTemplate template;
    private SocialNetworkUniverse socialNetworkUniverse;

    public void cleanDb() {
  //      Neo4jHelper.cleanDb(template.getGraphDatabaseService());
    }

    public void setUp() {
        socialNetworkUniverse = new SocialNetworkUniverse(template);
        socialNetworkUniverse.init();
    }

    @Transactional
    public void demoFindAUsersFriendsOfFriendsUsingTheirUserId() {

        String query
                = "match (n:User {userId: {userId}} )-[r:IS_FRIEND_OF]-(friend)-[r2:IS_FRIEND_OF]-(fof) "
                + "return distinct fof";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", "john001");
        Result<Map<String, Object>> result = template.query(query, params);
        Result<User> userResults = result.to(User.class);
        assertExpectedFriendsOfFriends(userResults);
    }

    @Transactional
    public void demoFindAUsersFriendsOfFriendsUsingTheirNodeId() {

        // Note: Generally you would not use nodeId's directly but rather
        //       lookup entities based on indexed properties as above,
        //       however this method provides a way to see the difference
        //       in the two mechanisms.
        String query = "match (n)-[r:IS_FRIEND_OF]-(friend)-[r2:IS_FRIEND_OF]-(fof) "
                + "where id(n) = {nodeId}"
                + "return distinct fof";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nodeId", socialNetworkUniverse.john.getNodeId());
        Result<Map<String, Object>> result = template.query(query, params);
        Result<User> userResults = result.to(User.class);
        assertExpectedFriendsOfFriends(userResults);

    }

    private void assertExpectedFriendsOfFriends(Result<User> userResults) {
        List<String> extractedUserIds = new ArrayList<String>();
        Iterator<User> it = userResults.iterator();
        while (it.hasNext()) {
            User val = it.next();
            extractedUserIds.add(val.getUserId());
        }

    }
}
