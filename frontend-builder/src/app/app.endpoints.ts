import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';

@Injectable()
export class AppEndpoints {
  host = environment.host;
  paths = {
    root: () => `/`,
    buildings: () => `${this.host}/buildings`,
    building: id => `${this.host}/buildings/${id}`,
  };
  go() {
    return this.paths;
  }
}
