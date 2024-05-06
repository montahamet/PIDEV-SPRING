    package com.coconsult.pidevspring.Services.HR;

    import com.coconsult.pidevspring.DAO.Entities.Candidacy;
    import com.coconsult.pidevspring.DAO.Entities.mark;
    import com.coconsult.pidevspring.DAO.Repository.HR.CandidacyRepository;
    import com.coconsult.pidevspring.DAO.Repository.HR.markRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class markService implements ImarkService{

        private final markRepository markRepository;
        CandidacyRepository candidacyRepository;


        @Autowired
        public markService(markRepository markRepository, CandidacyRepository candidacyRepository) {
            this.markRepository = markRepository;
            this.candidacyRepository = candidacyRepository;
        }

        @Override
        public mark addMark(mark mark) {
                return markRepository.save(mark);
        }
    }
