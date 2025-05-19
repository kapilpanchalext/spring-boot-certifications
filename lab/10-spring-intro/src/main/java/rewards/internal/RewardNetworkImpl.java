package rewards.internal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import common.money.MonetaryAmount;
import common.money.Percentage;
import rewards.AccountContribution;
import rewards.AccountContribution.Distribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.Account;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.Beneficiary;
import rewards.internal.restaurant.Restaurant;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;

/**
 * Rewards an Account for Dining at a Restaurant.
 * 
 * The sole Reward Network implementation. This object is an application-layer service responsible for coordinating with
 * the domain-layer to carry out the process of rewarding benefits to accounts for dining.
 * 
 * Said in other words, this class implements the "reward account for dining" use case.
 *
 * TODO1-00: In this lab, you are going to exercise the following:
 * - Understanding internal operations that need to be performed to implement
 *   "rewardAccountFor" method of the "RewardNetworkImpl" class
 * - Writing test code using stub implementations of dependencies
 * - Writing both target code and test code without using Spring framework
 *
 * TODO1-01: Review the Rewards Application document (Refer to the lab document)
 * TODO1-02: Review project dependencies (Refer to the lab document)
 * TODO1-03: Review Rewards Commons project (Refer to the lab document)
 * TODO1-04: Review RewardNetwork interface and RewardNetworkImpl class below
 * TODO1-05: Review the RewardNetworkImpl configuration logic (Refer to the lab document)
 * TODO1-06: Review sequence diagram (Refer to the lab document)
 */
public class RewardNetworkImpl implements RewardNetwork {

	private AccountRepository accountRepository;

	private RestaurantRepository restaurantRepository;

	private RewardRepository rewardRepository;

	/**
	 * Creates a new reward network.
	 * @param accountRepository the repository for loading accounts to reward
	 * @param restaurantRepository the repository for loading restaurants that determine how much to reward
	 * @param rewardRepository the repository for recording a record of successful reward transactions
	 */
	public RewardNetworkImpl(AccountRepository accountRepository, RestaurantRepository restaurantRepository,
			RewardRepository rewardRepository) {
		this.accountRepository = accountRepository;
		this.restaurantRepository = restaurantRepository;
		this.rewardRepository = rewardRepository;
	}

	public RewardConfirmation rewardAccountFor(Dining dining) {
		// TODO1-07: Write code here for rewarding an account according to
		//          the sequence diagram in the lab document

//		Restaurant restaurant = new Restaurant(dining.getMerchantNumber(), "Restaurant1");
//		restaurant.setBenefitPercentage(new Percentage(0.5));
		
//		Account savings = accountRepository.findByCreditCard(dining.getCreditCardNumber());
//		savings.addBeneficiary("Name1", new Percentage(0.5));
//		savings.addBeneficiary("Name2", new Percentage(0.5));
		
//		MonetaryAmount monetoryAmountToBeDividedAmongstChildren = dining.getAmount().multiplyBy(restaurant.getBenefitPercentage());
		
//		savings.makeContribution(monetoryAmountToBeDividedAmongstChildren);

//		AccountContribution accountContribution = new AccountContribution(dining.getCreditCardNumber(),
//				monetoryAmountToBeDividedAmongstChildren, distributionSet);
//		
//		RewardConfirmation rewardConfirmation = new RewardConfirmation(UUID.randomUUID().toString(), 
//				accountContribution);
		// TODO1-08: Return the corresponding reward confirmation
		
		Account account = accountRepository.findByCreditCard(dining.getCreditCardNumber());
		Restaurant restaurant = restaurantRepository.findByMerchantNumber(dining.getMerchantNumber());
		MonetaryAmount amount = restaurant.calculateBenefitFor(account, dining);
		AccountContribution contribution = account.makeContribution(amount);
		accountRepository.updateBeneficiaries(account);
		return rewardRepository.confirmReward(contribution, dining);
//		return rewardConfirmation;
	}
}
