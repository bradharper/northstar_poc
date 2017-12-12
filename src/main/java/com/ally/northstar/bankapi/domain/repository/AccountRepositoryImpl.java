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
package com.ally.northstar.bankapi.domain.repository;

import com.ally.northstar.bankapi.domain.model.Account;
import com.ally.northstar.bankapi.domain.model.Project;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ResourceRepositoryBase-based example with the base class providing some base functionality.
 */
@Component
public class AccountRepositoryImpl extends ResourceRepositoryBase<Account, Long> implements AccountRepository {

	private static final AtomicLong ID_GENERATOR = new AtomicLong(124);

	private Map<Long, Account> accounts = new HashMap<>();

	public AccountRepositoryImpl() {
		super(Account.class);
		List<String> interests = new ArrayList<>();
		interests.add("coding");
		interests.add("art");
		save(buildAccount(121L, "Savings", "111122223333"));
		save(buildAccount(122L, "Checking", "222233334444"));
		save(buildAccount(123L, "College Fund", "333344445555"));
		save(buildAccount(124L, "Vacation Fund", "444455556666"));

	}

	@Override
	public synchronized void delete(Long id) {
		accounts.remove(id);
	}

	@Override
	public synchronized <S extends Account> S save(S account) {
		if (account.getId() == null) {
			account.setId(ID_GENERATOR.getAndIncrement());
		}
		accounts.put(account.getId(), account);
		return account;
	}

	@Override
	public synchronized AccountList findAll(QuerySpec querySpec) {
		AccountList list = new AccountList();
		list.setMeta(new AccountListMeta());
		list.setLinks(new AccountListLinks());
		querySpec.apply(accounts.values(), list);
		return list;
	}

	private Account buildAccount(Long id, String name, String number){
		Account toReturn = new Account(id, name);

		toReturn.setAccountNickname("ACCT "+name+" NICKNAME");
		toReturn.setAccountNumberPvtEncrypt(number);
		toReturn.setAccountOpeningDate(new Timestamp(System.currentTimeMillis()));
		toReturn.setAccountStatus("Active");
		toReturn.setAccountType("SDA");
		toReturn.setApy(1.05);
		toReturn.setBankName("Ally");
		toReturn.setCfiid("7825610");
		toReturn.setAvailableBalancePvtEncrypt(252188F);
		toReturn.setCurrentBalancePvtEncrypt(252188F);
		toReturn.setExternalAccountIndicator(false);
		toReturn.setTransferFromIndicator(true);
		toReturn.setTransferToIndicator(true);
		toReturn.setRetirementIndicator(false);
		toReturn.setInterestYtd(1268.62F);
		toReturn.setRdcEligibilityIndicator(true);
		toReturn.setOwnershipType("B");

		return toReturn;
	}

}
