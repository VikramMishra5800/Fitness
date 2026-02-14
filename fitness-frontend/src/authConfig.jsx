export const authConfig  = {
  clientId: 'outh2-fitness-token-2',
  authorizationEndpoint: 'http://localhost:8181/realms/Fitness-Realm/protocol/openid-connect/auth',
  tokenEndpoint: 'http://localhost:8181/realms/Fitness-Realm/protocol/openid-connect/token',
  redirectUri: 'http://localhost:5173',
  scope: 'openid roles profile email',
  onRefreshTokenExpire: (event) => event.logIn(),
}
