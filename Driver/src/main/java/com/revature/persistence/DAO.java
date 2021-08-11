package com.revature.persistence;
import com.revature.collection.RevArrayList;
import com.revature.model.UserInfo;

/**DAO interface with abstract implementations.
 *
 */
public interface DAO {

    RevArrayList<UserInfo> getLoginInfo();

    void save(String username, String firstName, String lastName, String password, String email);
}
