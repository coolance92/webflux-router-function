package com.coolance.repository;

import com.coolance.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @ClassName UserRepository
 * @Description 仓库类
 * @Author Coolance
 * @Version
 * @Date 2019/10/13 14:35
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    /**
     * 根据年龄范围查找
     *
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start, int end);

    /**
     * 得到年龄在20<=age<=30用户
     *
     * @return
     */
    @Query("{'age':{'$gte':20, '$lte':30}}")
    Flux<User> oldUser();
}
