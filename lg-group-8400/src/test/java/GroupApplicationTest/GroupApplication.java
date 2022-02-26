package GroupApplicationTest;

import com.group8.GroupApplication8400;
import com.group8.dao.LgComboDao;
import com.group8.dao.LgGroupDao;
import com.group8.entity.LgCombo;
import com.group8.entity.LgGroup;
import com.group8.service.LgComboService;
import com.group8.service.LgGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * description: GroupApplication <br>
 * date: 2022/2/19 2:52 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@SpringBootTest(classes = GroupApplication8400.class)
public class GroupApplication {

    @Resource
    LgGroupService lgGroupService;

    @Resource
    LgComboDao lgComboDao;
    @Resource
    LgGroupDao lgGroupDao;
    @Resource
    LgComboService lgComboService;

    @Test
    public void test() {
        //LgGroup lgGroup = new LgGroup();
        //for (LgGroup group : lgGroupService.queryAll()) {
        //    System.out.println(group);
        //}

    }

    @Test
    public void test1() {
        //LgGroup lgGroup = new LgGroup();
        //lgGroup.setGroupId(1);
        //lgGroup.setGroupName("大理");
        //lgGroup.setGroupScore("3.6");
        //lgGroupService.update(lgGroup);
    }

    @Test
    public void test3() {
        boolean b = lgGroupService.deleteById(2);
        System.out.println(b);
    }

//    @Test
//    public void test5() {
//        LgGroup lgGroup = new LgGroup();
//        lgGroup.setBussId(1);
//        lgGroupService.insert(lgGroup);
//    }
////
//    @Test
//    public void test06(){
//        GroupAndComboDto groupAndComboDto = new GroupAndComboDto();
//        LgGroup lgGroup = new LgGroup();
//        lgGroup.setGroupName("出境2");
//        groupAndComboDto.setLgGroup(lgGroup);
//        lgGroupService.inserts(groupAndComboDto);
//    }

//    @Test
//    public void test07(){
//        GroupAndComboDto groupAndComboDto = new GroupAndComboDto();
//        LgGroup lgGroup = new LgGroup();
//        lgGroup.setGroupName("出境3");
//        groupAndComboDto.setLgGroup(lgGroup);
//        lgGroupService.insert(groupAndComboDto);
//    }

    @Test
    public void test08() {
        LgCombo lgCombo = new LgCombo();
        lgCombo.setComboId(1);
        lgCombo.setComboName("sss12");
        int i = lgComboService.insert(lgCombo);
        System.out.println(i);
    }

//    @Test
//    public void test09() {
////        GroupAndComboDto groupAndComboDto = new GroupAndComboDto();
//        LgGroup lgGroup = new LgGroup();
//        lgGroup.setGroupName("出境跟团");
//        lgGroupDao.insert(lgGroup);
////        int groupId = (int) lgGroup.getGroupId();
////        LgCombo lgCombo = new LgCombo();
////        lgCombo.setComboName("四人游1");
////        lgCombo.setGroupId(groupId);
////        lgComboDao.insert(lgCombo);
//    }

//    @Test
//    public void tets(){
//        GroupAndComboDto groupAndComboDto = new GroupAndComboDto();
//        LgGroup lgGroup = new LgGroup();
//        lgGroup.setGroupName("出境3");
//        LgCombo lgCombo = new LgCombo();
//        lgCombo.setComboName("eee");
//        groupAndComboDto.setLgGroup(lgGroup);
//        groupAndComboDto.setLgCombo(lgCombo);
//        lgGroupService.insert(groupAndComboDto);
//    }

    @Test
    void ts() {
        for (int i = 0; i < 100; i++) {
            LgCombo lgCombo = new LgCombo();
            lgCombo.setComboName("ss");
            ArrayList<LgCombo> k = new ArrayList<>();
            k.add(lgCombo);
            lgComboDao.insert(k);
        }
    }

    @Test
    void x() {
//        AvgMark avgMark = new AvgMark();
//        avgMark.setMark(1);
        LgGroup lgGroup = new LgGroup();
        lgGroup.setGroupId(1);
        lgGroup.setBussId(1);
        //lgGroup.setPid(1);
        lgGroup.setGroupName("中国");
//        lgGroup.setBussId(1);
//        lgGroup.setGroupName("ss");
//        System.out.println(lgGroupDao.update(lgGroup));
        //System.out.println(lgGroupDao.update(lgGroup));
        System.out.println(lgGroupService.update(lgGroup));
    }

//    @Test
//    void y(){
////        int upates = lgGroupDao.upates(1);
////        System.out.println(upates);
//////      lgGroupService.upates(1);
//        lgGroupDao.sorting();
//    }
}
