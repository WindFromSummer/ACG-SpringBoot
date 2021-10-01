package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.Ticket;
import zc.free.acg.model.TicketExample;

public interface TicketMapper {
    long countByExample(TicketExample example);

    int deleteByExample(TicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    List<Ticket> selectByExample(TicketExample example);

    Ticket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);
}