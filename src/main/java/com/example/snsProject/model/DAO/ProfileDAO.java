    package com.example.snsProject.model.DAO;

    import org.apache.ibatis.annotations.Mapper;

    import java.util.List;
    import java.util.Map;

    @Mapper
    public interface ProfileDAO {
        int CountPosts(long id);
        int CountFollowers(long id);
        int CountFollows(long id);

        List<Map<String,Object>> getProfileInfo(long member_id);
    }
