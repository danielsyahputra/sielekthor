package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.MemberModel;

import java.util.List;

public interface MemberService {
    void addMember(MemberModel member);
    List<MemberModel> getListMember();
}
