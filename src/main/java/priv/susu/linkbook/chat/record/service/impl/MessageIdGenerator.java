package priv.susu.linkbook.chat.record.service.impl;

import org.springframework.stereotype.Service;

@Service
public final class MessageIdGenerator {

	private long id = 0;

	public synchronized long next(int roomId) {
		return ++id;
	}

}
