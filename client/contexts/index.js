import {
	ProgressContext,
	ProgressProvider,
	ProgressConsumer,
} from './Progress';

const Providers = ({ children }) => {
	return <ProgressProvider>{children}</ProgressProvider>;
};

export { Providers, ProgressContext, ProgressConsumer };
