package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.repository.MemberDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberDb memberDb;

    @Override
    public void addMember(MemberModel member) {
        memberDb.save(member);
    }
}
