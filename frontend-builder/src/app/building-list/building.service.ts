import {Injectable} from '@angular/core';
import {Building} from '../building';
import {HttpClient} from '@angular/common/http';
import {AppEndpoints} from '../app.endpoints';

@Injectable()
export class BuildingService {

  constructor(private httpClient: HttpClient,
              private appEndpoints: AppEndpoints) {
  }

  getBuildings() {
    return this.httpClient.get<Building[]>(this.appEndpoints.go().buildings(), {observe: 'body'});
  }
}
