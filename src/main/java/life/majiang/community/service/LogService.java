package life.majiang.community.service;


import life.majiang.community.mapper.LogMapper;
import life.majiang.community.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public void sign(Log log){
        logMapper.create(log);
    }
}
