package com.fluidcodes.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fluidcodes.crm.dao.AccountsRepo;
import com.fluidcodes.crm.dao.TransactionsRepo;
import com.fluidcodes.crm.models.Accounts;
import com.fluidcodes.crm.models.Transactions;

/*
 * Implementing methods from spring jpa
 */
@Service
public class TransactionsService {

	@Autowired
	private TransactionsRepo transRepo;

	@Autowired
	private AccountsRepo accountRepo;

	public List<Transactions> findAll() {

		return transRepo.findAll();
	}

	public Transactions getOne(Long id) {

		return transRepo.getOne(id);
	}

	public Transactions findById(Long id) {

		return transRepo.findById(id).get();

	}

	public boolean existsById(Long id) {

		return transRepo.existsById(id);
	}

	public long count() {
		List<Transactions> usersCount = transRepo.findAll();

		return usersCount.size();
	}

	public void deleteById(Long id) {

		Transactions trans = null;
		Optional<Transactions> a = transRepo.findById(id);

		if (!a.isPresent()) {

			System.out.println("Transaction id dont exist");

		} else {

			trans = a.get();
			System.out.println("Set account: " + trans);
		}
		Long accId = trans.getAccount().getAccountId();
		transRepo.deleteById(id);

		Accounts acc = accountRepo.getOne(accId);

		System.out.println("account balance:" + acc.getBalance() + "trans value" + trans.getTransAmount());
		double accBalance = acc.getBalance();
		double transAmount = trans.getTransAmount();
		System.out.println("account balacne:" + accBalance);
		System.out.println("trans amount: " + transAmount);
		System.out.println("value of iscredit:" + trans.getTransIsCredit());

		if (trans.getTransIsCredit() == null || trans.getTransIsCredit() == false) {
			trans.setTransIsCredit(false);
			accBalance += transAmount;
			System.out.println("account delete expense" + accBalance);
			acc.setBalance(accBalance);

		}

		else if (trans.getTransIsCredit() == true) {
			accBalance -= transAmount;
			System.out.println("account delete credit" + accBalance);
			acc.setBalance(accBalance);
		}

		accountRepo.save(acc);

	}

	// saving new or updating a transaction
	public void save(Transactions newTrans, Long id) {

		// get account related to transaction and make sure it exist

		Accounts account = null;
		Optional<Accounts> a = accountRepo.findById(id);

		if (!a.isPresent()) {

			System.out.println("Account id dont exist");

		} else {

			account = a.get();
			System.out.println("Set account: " + account);
		}

		System.out.println("account balance:" + account.getBalance() + "trans value" + newTrans.getTransAmount());
		double accBalance = account.getBalance();
		double transAmount = newTrans.getTransAmount();
		System.out.println("account balacne:" + accBalance);
		System.out.println("trans amount: " + transAmount);
		System.out.println("value of iscredit:" + newTrans.getTransIsCredit());

		// if the transaction is new
		if (newTrans.getTransId() == null) {
			// if iscredit is null or false meaning its an expense
			if (newTrans.getTransIsCredit() == null || newTrans.getTransIsCredit() == false) {
				newTrans.setTransIsCredit(false);
				// account balance - the expense
				accBalance -= transAmount;
				System.out.println("balance for expense after new trans" + accBalance);
				account.setBalance(accBalance);

			}
			// if iscredit is true meaning its a credit
			else if (newTrans.getTransIsCredit() == true) {
				// account balance + income
				accBalance += transAmount;
				System.out.println("balance for income after new trans" + accBalance);
				account.setBalance(accBalance);
			}

			// if editing transaction
		} else {
			// get old transaction from db
			Transactions oldTrans = transRepo.getOne(newTrans.getTransId());
			double oldTransAmount = oldTrans.getTransAmount();
			// if transaction is credit field being updated is null meaning it was a credit
			// and became a an expense
			if (newTrans.getTransIsCredit() == null) {

				newTrans.setTransIsCredit(false);

				accBalance -= oldTransAmount;
				accBalance -= transAmount;
				System.out.println("balance for expense after edit trans" + accBalance);
				account.setBalance(accBalance);
				// if is credit is false meaning was an expense and still an expense
			} else if (newTrans.getTransIsCredit() == false) {
				accBalance += oldTransAmount;
				accBalance -= transAmount;
				System.out.println("balance for expense after edit trans" + accBalance);
				account.setBalance(accBalance);

			}
			// if is credit true meaning was a credit and still a credit
			else if (newTrans.getTransIsCredit() == true) {
				accBalance -= oldTransAmount;
				accBalance += transAmount;
				System.out.println("balance for income after edit trans" + accBalance);
				account.setBalance(accBalance);
			}

		}

		// save new or update transaction
		account.add(newTrans);
		System.out.println("Set accounts after add transaction: " + account);

		System.out.println("Set transactions after add account: " + newTrans);
		// accountRepo.save(account);
		transRepo.save(newTrans);

	}

}
