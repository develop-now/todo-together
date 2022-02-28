import { createGlobalStyle, css } from 'styled-components';
import reset from 'styled-reset';

const globalStyle = css`
	${reset}
	* {
		box-sizing: border-box;
	}
	html {
		height: 100%;
	}
	body {
		font-family: 'Noto Sans KR';
		height: 100%;
	}
	a {
		text-decoration: none;
	}
	a:link {
		color: #000000;
	}
	a:visited {
		color: #000000;
	}
	a:hover {
		text-decoration: underline;
	}
`;

const GlobalStyle = createGlobalStyle`
    ${globalStyle}
`;

export default GlobalStyle;
