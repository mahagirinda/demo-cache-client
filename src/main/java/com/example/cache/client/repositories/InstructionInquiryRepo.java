package com.example.cache.client.repositories;

import com.telkomsigma.eclears.app.common.viewentity.inquiry.ViewInquiryInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InstructionInquiryRepo extends JpaRepository<ViewInquiryInstruction, Long>, 
												JpaSpecificationExecutor<ViewInquiryInstruction>{

}
