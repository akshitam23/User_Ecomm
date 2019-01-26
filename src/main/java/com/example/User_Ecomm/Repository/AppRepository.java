package com.example.User_Ecomm.Repository;

import com.example.User_Ecomm.Entity.Address;
import com.example.User_Ecomm.Entity.App;
import com.example.User_Ecomm.Entity.Web;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends CrudRepository<App, String> {

    @Query(value = "SELECT * FROM APP where user_id=?1",nativeQuery = true)
 App findAppByUserId(String UserId);
    @Modifying
    @Query(value = "DELETE FROM APP WHERE user_id=:userId", nativeQuery = true)
    void logout(@Param("userId") String userId);
}
