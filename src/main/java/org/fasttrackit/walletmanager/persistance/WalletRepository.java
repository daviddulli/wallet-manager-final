package org.fasttrackit.walletmanager.persistance;

import org.fasttrackit.walletmanager.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
