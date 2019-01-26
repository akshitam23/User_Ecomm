package com.example.User_Ecomm.Repository;

import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.App;
import com.example.User_Ecomm.Entity.Users;
import com.example.User_Ecomm.Entity.Web;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WebRepository extends CrudRepository<Web, String> {


    @Query(value = "SELECT * FROM WEB where user_id=?1",nativeQuery = true)
Web findWebByUserId(String UserId);
    @Modifying
    @Query(value = "DELETE FROM WEB WHERE user_id=?1", nativeQuery = true)
    void logout(@Param("userId") String userId);

}
