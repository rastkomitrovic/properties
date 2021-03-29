package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "company_details")
data class CompanyDetails(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "company_details_id", nullable = false, unique = true) val companyDetailsId: Long,
        @Column(name = "company_name", nullable = false) val companyName: String,
        @Column(name = "company_address", nullable = false) val companyAddress: String,
        @Column(name = "company_email", nullable = false) val companyEmail: String,
        @Column(name = "company_phone_number", nullable = false) val companyPhoneNumber: String,
        @Column(name = "company_logo_url", nullable = false) val companyLogoUrl: String
)
