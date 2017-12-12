/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ally.northstar.bankapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.annotations.JsonApiToMany;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Help me Obiwan, you're my only hope...
 * <p>
 * <p>
 *
 * @author bradharper
 * @version 2.0
 * @created 12/12/17 9:23 AM
 */
@JsonApiResource(type = "accounts")
public class Account extends Model {

    @JsonProperty
    private String name;

    @JsonProperty
    private String cfiid;
    @JsonProperty
    private Float availableBalancePvtEncrypt;
    @JsonProperty
    private String accountStatus;
    @JsonProperty
    private Float currentBalancePvtEncrypt;
    @JsonProperty
    private Float interestYtd;
    @JsonProperty
    private Timestamp accountOpeningDate;
    @JsonProperty
    private String bankName;
    @JsonProperty
    private Double apy;
    @JsonProperty
    private Boolean externalAccountIndicator;
    @JsonProperty
    private Boolean transferFromIndicator;
    @JsonProperty
    private Boolean transferToIndicator;
    @JsonProperty
    private Boolean retirementIndicator;
    @JsonProperty
    private Boolean rdcEligibilityIndicator;
    @JsonProperty
    private String accountNumberPvtEncrypt;
    @JsonProperty
    private String accountType;
    @JsonProperty
    private String accountNickname;
    @JsonProperty
    private String ownershipType;

    @JsonApiToMany(opposite = "account")
    private List<Product> products = new ArrayList<>();

    public Account() {
    }

    public Account(Long id) {
        this.id = id;
    }

    public Account(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCfiid() {
        return cfiid;
    }

    public void setCfiid(String cfiid) {
        this.cfiid = cfiid;
    }

    public Float getAvailableBalancePvtEncrypt() {
        return availableBalancePvtEncrypt;
    }

    public void setAvailableBalancePvtEncrypt(Float availableBalancePvtEncrypt) {
        this.availableBalancePvtEncrypt = availableBalancePvtEncrypt;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Float getCurrentBalancePvtEncrypt() {
        return currentBalancePvtEncrypt;
    }

    public void setCurrentBalancePvtEncrypt(Float currentBalancePvtEncrypt) {
        this.currentBalancePvtEncrypt = currentBalancePvtEncrypt;
    }

    public Float getInterestYtd() {
        return interestYtd;
    }

    public void setInterestYtd(Float interestYtd) {
        this.interestYtd = interestYtd;
    }

    public Timestamp getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(Timestamp accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getApy() {
        return apy;
    }

    public void setApy(Double apy) {
        this.apy = apy;
    }

    public Boolean getExternalAccountIndicator() {
        return externalAccountIndicator;
    }

    public void setExternalAccountIndicator(Boolean externalAccountIndicator) {
        this.externalAccountIndicator = externalAccountIndicator;
    }

    public Boolean getTransferFromIndicator() {
        return transferFromIndicator;
    }

    public void setTransferFromIndicator(Boolean transferFromIndicator) {
        this.transferFromIndicator = transferFromIndicator;
    }

    public Boolean getTransferToIndicator() {
        return transferToIndicator;
    }

    public void setTransferToIndicator(Boolean transferToIndicator) {
        this.transferToIndicator = transferToIndicator;
    }

    public Boolean getRetirementIndicator() {
        return retirementIndicator;
    }

    public void setRetirementIndicator(Boolean retirementIndicator) {
        this.retirementIndicator = retirementIndicator;
    }

    public Boolean getRdcEligibilityIndicator() {
        return rdcEligibilityIndicator;
    }

    public void setRdcEligibilityIndicator(Boolean rdcEligibilityIndicator) {
        this.rdcEligibilityIndicator = rdcEligibilityIndicator;
    }

    public String getAccountNumberPvtEncrypt() {
        return accountNumberPvtEncrypt;
    }

    public void setAccountNumberPvtEncrypt(String accountNumberPvtEncrypt) {
        this.accountNumberPvtEncrypt = accountNumberPvtEncrypt;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNickname() {
        return accountNickname;
    }

    public void setAccountNickname(String accountNickname) {
        this.accountNickname = accountNickname;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }
}
