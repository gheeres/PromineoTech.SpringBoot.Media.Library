const DefaultBaseUrl = 'http://localhost:8080';

export default class WorldService {
  constructor(baseUrl) {
    this.baseUrl = baseUrl || DefaultBaseUrl;
  }
};