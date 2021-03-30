package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "company_details")
open class CompanyDetails(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "company_details_id", nullable = false, unique = true) open val companyDetailsId: Long? = null,
        @Column(name = "company_name", nullable = false) open val companyName: String? = null,
        @Column(name = "company_address", nullable = false) open val companyAddress: String? = null,
        @Column(name = "company_email", nullable = false) open val companyEmail: String? = null,
        @Column(name = "company_phone_number", nullable = false) open val companyPhoneNumber: String? = null,
        @Column(name = "company_logo_url", nullable = false) open val companyLogoUrl: String? = null
) : BaseEntity()
