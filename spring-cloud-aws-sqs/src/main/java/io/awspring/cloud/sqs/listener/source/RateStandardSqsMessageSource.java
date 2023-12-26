/*
 * Copyright 2013-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.awspring.cloud.sqs.listener.source;

import io.awspring.cloud.sqs.listener.BackPressureMode;
import io.awspring.cloud.sqs.listener.RateSemaphoreBackPressureHandler;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementExecutor;
import java.time.Duration;

/**
 * {@link AbstractSqsMessageSource} implementation for standard queues.
 *
 * @author Tomaz Fernandes
 * @since 3.0
 */
public class RateStandardSqsMessageSource<T> extends AbstractSqsMessageSource<T> {

	@Override
	protected AcknowledgementExecutor<T> createAcknowledgementExecutorInstance() {
		this.setBackPressureHandler(RateSemaphoreBackPressureHandler.builder().acquireTimeout(Duration.ofSeconds(5))
				.batchSize(5).totalPermits(2).throughputConfiguration(BackPressureMode.AUTO).build());
		return super.createAcknowledgementExecutorInstance();
	}
}
