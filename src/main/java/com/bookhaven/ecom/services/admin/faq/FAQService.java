package com.bookhaven.ecom.services.admin.faq;

import com.bookhaven.ecom.dto.FAQDto;

public interface FAQService {
	FAQDto postFAQ(Long productId, FAQDto faqDto);
}
