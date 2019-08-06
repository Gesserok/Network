package ua.com.alparibank.network.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alparibank.network.annotations.Profiling;
import ua.com.alparibank.network.model.LocationAddr;

@Profiling
public interface LocationAddrRepository extends JpaRepository<LocationAddr, Integer> {
}
